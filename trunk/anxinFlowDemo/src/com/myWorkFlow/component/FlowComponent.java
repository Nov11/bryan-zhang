package com.myWorkFlow.component;

import java.util.List;

import com.myWorkFlow.base.FlowContext;
import com.myWorkFlow.base.CmpTypeEnum;
import com.myWorkFlow.event.FlowCmpRegEvent;
import com.myWorkFlow.util.ApplicationContextHolder;

public abstract class FlowComponent {
	
	//������ڵ�����
	private CmpTypeEnum typeEnum;
	
	//�����Ӧ��Keyֵ
	private List<String> keyList;
	
	//�������ڵ�ͬһ�����ҵ�������ʱ
	private int index;
	
	//�����ʼ������������spring�ļ������ƣ���OSGI����Ҫ
	public void init(){
		FlowCmpRegEvent event = new FlowCmpRegEvent(this);
		ApplicationContextHolder.getApplicationContext().publishEvent(event);
	}
	
	public abstract boolean excuteWithContext(FlowContext context);
	
	public boolean isBuild(FlowContext context){
		//�Ƿ�װ��
		return true;
	}
	
	public void rollbackWithContext(FlowContext context){
		//����о����ิд��û�оͲ���д
	}

	public CmpTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(CmpTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public List<String> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}