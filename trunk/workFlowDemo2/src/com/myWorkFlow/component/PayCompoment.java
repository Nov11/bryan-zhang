package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class PayCompoment extends FlowComponent {

	public void excuteWithContext(FlowContext context) {
		System.out.println("����pfȥ���֧��");
	}
	
	public void rollbackWithContext(FlowContext context) {
		System.out.println("�ع�֧��");
		
	}

}
