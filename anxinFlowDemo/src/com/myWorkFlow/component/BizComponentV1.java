package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class BizComponentV1 extends FlowComponent {

	public boolean excuteWithContext(FlowContext context) {
		System.out.println("��ȡBiz������ҵ��V1");
		return true;
	}
	
	public void rollbackWithContext(FlowContext context) {
		System.out.println("�ع�ҵ��");
	}

}
