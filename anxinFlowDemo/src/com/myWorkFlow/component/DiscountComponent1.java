package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class DiscountComponent1 extends FlowComponent {

	@Override
	public boolean excuteWithContext(FlowContext context) {
		System.out.println("ִ�еֿ۲���1");
		return true;
	}
	
	public void rollbackWithContext(FlowContext context){
		System.out.println("�ع��ֿ۲���1");
	}

}
