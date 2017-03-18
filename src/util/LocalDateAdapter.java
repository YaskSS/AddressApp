package util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/*
 * ������� (��� JAXB) ��� �������������� ����� ����� LocalDate � ���������
 * �������������� ���� � ��������� ISO 8601, �������� ��� '2012-12-03'.
 * 
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override
	public String marshal(LocalDate v) throws Exception {
		// TODO Auto-generated method stub
		return v.toString();
	}

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		// TODO Auto-generated method stub
		return LocalDate.parse(v);
	}

	
}
