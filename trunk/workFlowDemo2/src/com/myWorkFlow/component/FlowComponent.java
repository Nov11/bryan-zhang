package com.myWorkFlow.component;

import java.util.List;

import com.myWorkFlow.base.FlowContext;
import com.myWorkFlow.base.FlowTypeEnum;
import com.myWorkFlow.event.FlowCmpRegEvent;
import com.myWorkFlow.util.ApplicationContextHolder;

public abstract class FlowComponent {
	
	//������ڵ�����
	private FlowTypeEnum typeEnum;
	
	//�����Ӧ��Keyֵ
	private List<String> keyList; 
	
	//�����ʼ������������spring�ļ������ƣ���OSGI����Ҫ
	public void init(){
		FlowCmpRegEvent event = new FlowCmpRegEvent(this);
		ApplicationContextHolder.getApplicationContext().publishEvent(event);
	}
	
	public abstract void excuteWithContext(FlowContext context);
	
	public void rollbackWithContext(FlowContext context){
		//����о����ิд��û�оͲ���д
	}

	public FlowTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(FlowTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public List<String> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}
}
