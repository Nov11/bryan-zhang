package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class BizComponent extends FlowComponent {

	public boolean excuteWithContext(FlowContext context) {
		System.out.println("��ȡBiz������ҵ��");
		return true;
	}
	
	public void rollbackWithContext(FlowContext context) {
		System.out.println("�ع�ҵ��");
	}

}
