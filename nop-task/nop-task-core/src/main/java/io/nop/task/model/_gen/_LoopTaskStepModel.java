package io.nop.task.model._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.nop.task.model.LoopTaskStepModel;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from [168:14:0:0]/nop/schema/task/task.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _LoopTaskStepModel extends io.nop.task.model.TaskStepsModel {
    
    /**
     *  
     * xml name: indexName
     * 
     */
    private java.lang.String _indexName ;
    
    /**
     *  
     * xml name: items
     * 
     */
    private io.nop.core.lang.eval.IEvalAction _items ;
    
    /**
     *  
     * xml name: maxCount
     * 
     */
    private int _maxCount  = 0;
    
    /**
     *  
     * xml name: until
     * 
     */
    private io.nop.core.lang.eval.IEvalPredicate _until ;
    
    /**
     *  
     * xml name: varName
     * 
     */
    private java.lang.String _varName ;
    
    /**
     * 
     * xml name: indexName
     *  
     */
    
    public java.lang.String getIndexName(){
      return _indexName;
    }

    
    public void setIndexName(java.lang.String value){
        checkAllowChange();
        
        this._indexName = value;
           
    }

    
    /**
     * 
     * xml name: items
     *  
     */
    
    public io.nop.core.lang.eval.IEvalAction getItems(){
      return _items;
    }

    
    public void setItems(io.nop.core.lang.eval.IEvalAction value){
        checkAllowChange();
        
        this._items = value;
           
    }

    
    /**
     * 
     * xml name: maxCount
     *  
     */
    
    public int getMaxCount(){
      return _maxCount;
    }

    
    public void setMaxCount(int value){
        checkAllowChange();
        
        this._maxCount = value;
           
    }

    
    /**
     * 
     * xml name: until
     *  
     */
    
    public io.nop.core.lang.eval.IEvalPredicate getUntil(){
      return _until;
    }

    
    public void setUntil(io.nop.core.lang.eval.IEvalPredicate value){
        checkAllowChange();
        
        this._until = value;
           
    }

    
    /**
     * 
     * xml name: varName
     *  
     */
    
    public java.lang.String getVarName(){
      return _varName;
    }

    
    public void setVarName(java.lang.String value){
        checkAllowChange();
        
        this._varName = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("indexName",this.getIndexName());
        out.putNotNull("items",this.getItems());
        out.putNotNull("maxCount",this.getMaxCount());
        out.putNotNull("until",this.getUntil());
        out.putNotNull("varName",this.getVarName());
    }

    public LoopTaskStepModel cloneInstance(){
        LoopTaskStepModel instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(LoopTaskStepModel instance){
        super.copyTo(instance);
        
        instance.setIndexName(this.getIndexName());
        instance.setItems(this.getItems());
        instance.setMaxCount(this.getMaxCount());
        instance.setUntil(this.getUntil());
        instance.setVarName(this.getVarName());
    }

    protected LoopTaskStepModel newInstance(){
        return (LoopTaskStepModel) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
