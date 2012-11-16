package com.myWorkFlow.test;

import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.myWorkFlow.base.FlowContext;
import com.myWorkFlow.base.FlowExcutor;

/**
 * 
 * �����������ʱԼ��"��Ϣ����_ҵ����_֧��ͨ��_���к�" ���ָ�ʽ
 *
 */

public class TestMain {

	@Test
	public void test1(){
		String xmlCtxPath1 = "classpath:componentContext.xml";
		String xmlCtxPath2 = "classpath:flowContext.xml";
		
		new FileSystemXmlApplicationContext(new String[]{xmlCtxPath2,xmlCtxPath1});
		
		FlowContext context = new FlowContext();
		FlowExcutor excutor = new FlowExcutor(context);
		//���´�������Ĭ������
//		excutor.excute("0100_62_04_00");//���е�type����
//		excutor.excute("0100_50_04_00");//û�еֿ�type
//		excutor.excute("0100_61_04_00");//��2���ֿ�����
		
		//���´�������relation1
		excutor.excute("0100_50_01_00");//��relation1���̣�����biz����֧��
		
		
	}

	
	
}
