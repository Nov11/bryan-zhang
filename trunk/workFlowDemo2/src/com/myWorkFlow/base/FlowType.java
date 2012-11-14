package com.myWorkFlow.base;

import java.util.List;

import com.myWorkFlow.component.FlowComponent;

public class FlowType {
	//������Ͷ�Ӧ�ľ������������������ֻ��һ��
	private List<FlowComponent> componentList;

	//�����������������
	private FlowTypeEnum typeEnum;
	
	public void addComponent(FlowComponent component){
		this.componentList.add(component);
	}
	
	public void removeComponent(FlowComponent component){
		this.componentList.remove(component);
	}
	
	public List<FlowComponent> getComponentList() {
		return componentList;
	}

	public void setComponentList(List<FlowComponent> componentList) {
		this.componentList = componentList;
	}

	public FlowTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(FlowTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}
}
