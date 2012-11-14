package com.myWorkFlow.base;

import java.util.List;

import com.myWorkFlow.event.FlowRlaRegEvent;
import com.myWorkFlow.util.ApplicationContextHolder;

public class FlowRelation {
	private List<FlowType> flowTypeList;
	
	private List<String> keyList;

	//�����ʼ������������spring�ļ������ƣ���OSGI����Ҫ
	public void init(){
		FlowRlaRegEvent event = new FlowRlaRegEvent(this);
		ApplicationContextHolder.getApplicationContext().publishEvent(event);
	}
	
	public List<FlowType> getFlowTypeList() {
		return flowTypeList;
	}

	public void setFlowTypeList(List<FlowType> flowTypeList) {
		this.flowTypeList = flowTypeList;
	}

	public List<String> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}
}
