package com.myWorkFlow.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.myWorkFlow.component.FlowComponent;

public class Flow {
	private List<ComponentType> cmpTypeList = new ArrayList<ComponentType>();
	
	//��ȡ��FLOW�����е�˳��Component
	public List<FlowComponent> getAllComponent(){
		List<FlowComponent> cmpList = new ArrayList<FlowComponent>();
		List<FlowComponent> cmpInnerList = null;
		for (ComponentType type : cmpTypeList){
			cmpInnerList = type.getComponentList();
			//����index����
			Collections.sort(cmpInnerList, new Comparator<FlowComponent>() {
				public int compare(FlowComponent o1, FlowComponent o2) {
					return (o1.getIndex() - o2.getIndex());
				};
			});
			for (FlowComponent cmp : cmpInnerList){
				cmpList.add(cmp);
			}
		}
		return cmpList;
	}
	
	public void addComponentType(ComponentType type){
		this.cmpTypeList.add(type);
	}

	public List<ComponentType> getCmpTypeList() {
		return cmpTypeList;
	}

	public void setCmpTypeList(List<ComponentType> cmpTypeList) {
		this.cmpTypeList = cmpTypeList;
	}
}
