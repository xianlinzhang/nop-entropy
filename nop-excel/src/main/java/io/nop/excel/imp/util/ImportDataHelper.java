package io.nop.excel.imp.util;

import io.nop.api.core.convert.ConvertHelper;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.util.Guard;
import io.nop.commons.collections.KeyedList;
import io.nop.commons.util.StringHelper;
import io.nop.core.reflect.bean.BeanTool;

import java.util.ArrayList;
import java.util.List;

import static io.nop.api.core.ApiErrors.ARG_PROP_NAME;


public class ImportDataHelper {
    /**
     * 将列表数据整理成parent-children结构。例如
     * <pre>{@code
     *  [ { name: 'A1', value:1,level:0}, { name: 'A1-1',value:2, level:1 }, { name: 'A1-2',value:3,level:1}]
     *
     *  将被转换为
     *  [ {name: 'A1',value:1, children:[
     *      { name: 'A1-1', value:2},
     *      { name: 'A1-2', value:3}
     *  ] }
     *
     * }
     * </pre>{}
     *
     * @param list         按照父子关系排序的列表
     * @param childrenProp 子对象集合对应的属性名
     * @param levelProp    对象的嵌套层级
     */
    public static void normalizeTree(List<Object> list, String childrenProp,
                                     String levelProp, String childKeyProp) {
        if (list.size() <= 1)
            return;

        Guard.notEmpty(childrenProp, "childrenProp");
        Guard.notEmpty(levelProp, "levelProp");

        if (StringHelper.isEmpty(childKeyProp))
            childKeyProp = null;

        List<Item> stack = new ArrayList<>();
        stack.add(makeItem(list.get(0), levelProp));

        for (int i = 1, n = list.size(); i < n; i++) {
            Item item = makeItem(list.get(i), levelProp);
            Item parent = findParentItem(stack, item.level);

            if (parent != null) {
                // 子节点
                parent.makeChildren(childrenProp, childKeyProp).add(item.record);
                list.remove(i);
                i--;
                n--;
            }
            stack.add(item);
        }
    }

    private static Item findParentItem(List<Item> stack, int level) {
        while (!stack.isEmpty()) {
            Item item = stack.get(stack.size() - 1);
            if (item.level < level)
                return item;

            stack.remove(stack.size() - 1);
        }
        return null;
    }

    private static Item makeItem(Object record, String levelProp) {
        int level = ConvertHelper.toPrimitiveInt(BeanTool.getProperty(record, levelProp),
                err -> new NopException(err).param(ARG_PROP_NAME, levelProp));

        Item item = new Item();
        item.record = record;
        item.level = level;
        return item;
    }

    static class Item {
        Object record;
        int level;
        List<Object> children;

        List<Object> makeChildren(String childrenProp, String childKeyProp) {
            if (children == null) {
                children = childKeyProp == null ? new ArrayList<>() : new KeyedList<>(obj -> BeanTool.getProperty(obj, childKeyProp));
                BeanTool.instance().setProperty(record, childrenProp, children);
            }
            return children;
        }
    }
}