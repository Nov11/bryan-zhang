package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class DiscountComponent extends FlowComponent {

	@Override
	public boolean excuteWithContext(FlowContext context) {
		System.out.println("ִ�еֿ۲���");
		return true;
	}
	
	public void rollbackWithContext(FlowContext context){
		System.out.println("�ع��ֿ۲���");
	}

}
