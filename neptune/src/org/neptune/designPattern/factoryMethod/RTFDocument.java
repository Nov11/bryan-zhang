package org.neptune.designPattern.factoryMethod;

public class RTFDocument implements IDocument {
	public RTFDocument() { 
        System.out.println("����RTF�ļ�"); 
    }

    public void open() { 
        System.out.println("�����ļ�"); 
    }

    public void save() { 
        System.out.println("�����ļ�"); 
    }

    public void close() { 
        System.out.println("�ر��ļ�"); 
    }
}
