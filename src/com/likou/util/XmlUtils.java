package com.likou.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlUtils {

	@SuppressWarnings("rawtypes")
	public static String toXml(Object object, Class[] clazz) {
		XStream xStream = new XStream(new DomDriver());
		for (Class class1 : clazz) {
			xStream.alias(class1.getSimpleName(), class1);
		}
		xStream.autodetectAnnotations(true);
		return xStream.toXML(object);
	}

	/**
	 * 将传入xml文本转换成Java对象
	 * 
	 * @Title: toBean
	 * @Description: TODO
	 * @param xmlStr
	 * @param cls
	 *            xml对应的class类
	 * @return T xml对应的class类的实例对象 调用的方法实例
	 * 
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T toBean(String xml, Class[] cls) throws Exception {
		// 注意：不是new Xstream(); 否则报错：java.lang.NoClassDefFoundError:
		// org/xmlpull/v1/XmlPullParserFactory
		XStream xstream = new XStream(new DomDriver());
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(cls);
		T obj = (T) xstream.fromXML(xml);
		return obj;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T JsontoBean(String json, Class[] cls) throws Exception {
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
//		for (Class class1 : cls) {
//			xstream.alias(class1.getSimpleName(), class1);
//		}
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(cls);
		T obj = (T) xstream.fromXML(json);
		return obj;
	}

}
