package io.nop.fsm.model._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.nop.fsm.model.StateMachineModel;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /nop/schema/biz/state-machine.xdef <p>
 * 与XState库的概念基本保持一致。为了简化设计，只支持单一状态表示，不支持并行状态和历史状态。这样状态信息可以作为一个字段存放到数据库中。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _StateMachineModel extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: handle-error
     * 状态迁移出现异常时触发的监听函数。如果返回true，则认为异常已经被处理，不对外抛出异常
     */
    private io.nop.core.lang.eval.IEvalFunction _handleError ;
    
    /**
     *  
     * xml name: ignoreUnknownTransition
     * 
     */
    private boolean _ignoreUnknownTransition  = false;
    
    /**
     *  
     * xml name: initial
     * 初始状态的id
     */
    private java.lang.String _initial ;
    
    /**
     *  
     * xml name: invoke-action
     * 
     */
    private io.nop.core.lang.eval.IEvalFunction _invokeAction ;
    
    /**
     *  
     * xml name: message-type-getter
     * 接收到消息对象后可以根据得到消息类型，然后再结合prefix（如`send_`和`recv_`）得到用于触发StateMachine的event
     */
    private io.nop.core.lang.eval.IEvalFunction _messageTypeGetter ;
    
    /**
     *  
     * xml name: messageTypeProp
     * 
     */
    private java.lang.String _messageTypeProp ;
    
    /**
     *  
     * xml name: meta
     * 
     */
    private java.util.Map<java.lang.String,java.lang.Object> _meta ;
    
    /**
     *  
     * xml name: on-entry
     * 进入状态时触发的监听函数
     */
    private io.nop.core.lang.eval.IEvalAction _onEntry ;
    
    /**
     *  
     * xml name: on-exit
     * 离开状态时触发的监听函数
     */
    private io.nop.core.lang.eval.IEvalAction _onExit ;
    
    /**
     *  
     * xml name: stateProp
     * 实体上的状态属性名
     */
    private java.lang.String _stateProp ;
    
    /**
     *  
     * xml name: stateValueType
     * 
     */
    private io.nop.commons.type.StdDataType _stateValueType ;
    
    /**
     *  
     * xml name: state
     * 
     */
    private KeyedList<io.nop.fsm.model.StateModel> _states = KeyedList.emptyList();
    
    /**
     *  
     * xml name: var
     * IEvalScope中可以访问的变量
     */
    private KeyedList<io.nop.fsm.model.StateMachineVarModel> _vars = KeyedList.emptyList();
    
    /**
     * 
     * xml name: handle-error
     *  状态迁移出现异常时触发的监听函数。如果返回true，则认为异常已经被处理，不对外抛出异常
     */
    
    public io.nop.core.lang.eval.IEvalFunction getHandleError(){
      return _handleError;
    }

    
    public void setHandleError(io.nop.core.lang.eval.IEvalFunction value){
        checkAllowChange();
        
        this._handleError = value;
           
    }

    
    /**
     * 
     * xml name: ignoreUnknownTransition
     *  
     */
    
    public boolean isIgnoreUnknownTransition(){
      return _ignoreUnknownTransition;
    }

    
    public void setIgnoreUnknownTransition(boolean value){
        checkAllowChange();
        
        this._ignoreUnknownTransition = value;
           
    }

    
    /**
     * 
     * xml name: initial
     *  初始状态的id
     */
    
    public java.lang.String getInitial(){
      return _initial;
    }

    
    public void setInitial(java.lang.String value){
        checkAllowChange();
        
        this._initial = value;
           
    }

    
    /**
     * 
     * xml name: invoke-action
     *  
     */
    
    public io.nop.core.lang.eval.IEvalFunction getInvokeAction(){
      return _invokeAction;
    }

    
    public void setInvokeAction(io.nop.core.lang.eval.IEvalFunction value){
        checkAllowChange();
        
        this._invokeAction = value;
           
    }

    
    /**
     * 
     * xml name: message-type-getter
     *  接收到消息对象后可以根据得到消息类型，然后再结合prefix（如`send_`和`recv_`）得到用于触发StateMachine的event
     */
    
    public io.nop.core.lang.eval.IEvalFunction getMessageTypeGetter(){
      return _messageTypeGetter;
    }

    
    public void setMessageTypeGetter(io.nop.core.lang.eval.IEvalFunction value){
        checkAllowChange();
        
        this._messageTypeGetter = value;
           
    }

    
    /**
     * 
     * xml name: messageTypeProp
     *  
     */
    
    public java.lang.String getMessageTypeProp(){
      return _messageTypeProp;
    }

    
    public void setMessageTypeProp(java.lang.String value){
        checkAllowChange();
        
        this._messageTypeProp = value;
           
    }

    
    /**
     * 
     * xml name: meta
     *  
     */
    
    public java.util.Map<java.lang.String,java.lang.Object> getMeta(){
      return _meta;
    }

    
    public void setMeta(java.util.Map<java.lang.String,java.lang.Object> value){
        checkAllowChange();
        
        this._meta = value;
           
    }

    
    public boolean hasMeta(){
        return this._meta != null && !this._meta.isEmpty();
    }
    
    /**
     * 
     * xml name: on-entry
     *  进入状态时触发的监听函数
     */
    
    public io.nop.core.lang.eval.IEvalAction getOnEntry(){
      return _onEntry;
    }

    
    public void setOnEntry(io.nop.core.lang.eval.IEvalAction value){
        checkAllowChange();
        
        this._onEntry = value;
           
    }

    
    /**
     * 
     * xml name: on-exit
     *  离开状态时触发的监听函数
     */
    
    public io.nop.core.lang.eval.IEvalAction getOnExit(){
      return _onExit;
    }

    
    public void setOnExit(io.nop.core.lang.eval.IEvalAction value){
        checkAllowChange();
        
        this._onExit = value;
           
    }

    
    /**
     * 
     * xml name: stateProp
     *  实体上的状态属性名
     */
    
    public java.lang.String getStateProp(){
      return _stateProp;
    }

    
    public void setStateProp(java.lang.String value){
        checkAllowChange();
        
        this._stateProp = value;
           
    }

    
    /**
     * 
     * xml name: stateValueType
     *  
     */
    
    public io.nop.commons.type.StdDataType getStateValueType(){
      return _stateValueType;
    }

    
    public void setStateValueType(io.nop.commons.type.StdDataType value){
        checkAllowChange();
        
        this._stateValueType = value;
           
    }

    
    /**
     * 
     * xml name: state
     *  
     */
    
    public java.util.List<io.nop.fsm.model.StateModel> getStates(){
      return _states;
    }

    
    public void setStates(java.util.List<io.nop.fsm.model.StateModel> value){
        checkAllowChange();
        
        this._states = KeyedList.fromList(value, io.nop.fsm.model.StateModel::getId);
           
    }

    
    public io.nop.fsm.model.StateModel getState(String name){
        return this._states.getByKey(name);
    }

    public boolean hasState(String name){
        return this._states.containsKey(name);
    }

    public void addState(io.nop.fsm.model.StateModel item) {
        checkAllowChange();
        java.util.List<io.nop.fsm.model.StateModel> list = this.getStates();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.nop.fsm.model.StateModel::getId);
            setStates(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_states(){
        return this._states.keySet();
    }

    public boolean hasStates(){
        return !this._states.isEmpty();
    }
    
    /**
     * 
     * xml name: var
     *  IEvalScope中可以访问的变量
     */
    
    public java.util.List<io.nop.fsm.model.StateMachineVarModel> getVars(){
      return _vars;
    }

    
    public void setVars(java.util.List<io.nop.fsm.model.StateMachineVarModel> value){
        checkAllowChange();
        
        this._vars = KeyedList.fromList(value, io.nop.fsm.model.StateMachineVarModel::getName);
           
    }

    
    public io.nop.fsm.model.StateMachineVarModel getVar(String name){
        return this._vars.getByKey(name);
    }

    public boolean hasVar(String name){
        return this._vars.containsKey(name);
    }

    public void addVar(io.nop.fsm.model.StateMachineVarModel item) {
        checkAllowChange();
        java.util.List<io.nop.fsm.model.StateMachineVarModel> list = this.getVars();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.nop.fsm.model.StateMachineVarModel::getName);
            setVars(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_vars(){
        return this._vars.keySet();
    }

    public boolean hasVars(){
        return !this._vars.isEmpty();
    }
    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._states = io.nop.api.core.util.FreezeHelper.deepFreeze(this._states);
            
           this._vars = io.nop.api.core.util.FreezeHelper.deepFreeze(this._vars);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("handleError",this.getHandleError());
        out.putNotNull("ignoreUnknownTransition",this.isIgnoreUnknownTransition());
        out.putNotNull("initial",this.getInitial());
        out.putNotNull("invokeAction",this.getInvokeAction());
        out.putNotNull("messageTypeGetter",this.getMessageTypeGetter());
        out.putNotNull("messageTypeProp",this.getMessageTypeProp());
        out.putNotNull("meta",this.getMeta());
        out.putNotNull("onEntry",this.getOnEntry());
        out.putNotNull("onExit",this.getOnExit());
        out.putNotNull("stateProp",this.getStateProp());
        out.putNotNull("stateValueType",this.getStateValueType());
        out.putNotNull("states",this.getStates());
        out.putNotNull("vars",this.getVars());
    }

    public StateMachineModel cloneInstance(){
        StateMachineModel instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(StateMachineModel instance){
        super.copyTo(instance);
        
        instance.setHandleError(this.getHandleError());
        instance.setIgnoreUnknownTransition(this.isIgnoreUnknownTransition());
        instance.setInitial(this.getInitial());
        instance.setInvokeAction(this.getInvokeAction());
        instance.setMessageTypeGetter(this.getMessageTypeGetter());
        instance.setMessageTypeProp(this.getMessageTypeProp());
        instance.setMeta(this.getMeta());
        instance.setOnEntry(this.getOnEntry());
        instance.setOnExit(this.getOnExit());
        instance.setStateProp(this.getStateProp());
        instance.setStateValueType(this.getStateValueType());
        instance.setStates(this.getStates());
        instance.setVars(this.getVars());
    }

    protected StateMachineModel newInstance(){
        return (StateMachineModel) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
