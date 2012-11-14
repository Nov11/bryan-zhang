package com.myWorkFlow.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.myWorkFlow.base.FlowContext;
import com.myWorkFlow.base.FlowRelation;
import com.myWorkFlow.event.FlowRlaRegEvent;
import com.myWorkFlow.util.ApplicationContextHolder;

public class FlowRlaManage implements ApplicationListener {

	private static Map<String, FlowRelation> flowRlaMap = new HashMap<String, FlowRelation>();
	
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof FlowRlaRegEvent){
			FlowRelation fr = (FlowRelation)event.getSource();
			this.onBind(fr);
		}
	}
	
	public static FlowRelation getFlowRla(String key){
		FlowRelation flowRla = null;
		flowRla = flowRlaMap.get(key);
		//�����ȡ�������̹�ϵ����ȡĬ�ϵ����̹�ϵ
		if (flowRla == null){
			flowRla = (FlowRelation)ApplicationContextHolder.getBean("defaultRelation");
		}
		return flowRla;
	}
	
	public static FlowRelation getFlow(FlowContext context){
		String key = null;
		//TODO��context���ȡkey
		return getFlowRla(key);
	}
	
	
	public void onBind(FlowRelation flowRla){
		List<String> keyList = flowRla.getKeyList();
		for (String key : keyList){
			this.flowRlaMap.put(key, flowRla);
		}
	}

}
