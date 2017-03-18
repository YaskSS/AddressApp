package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Person")
public class PersonListWrapper {
	 private List<Person> persons;

	    @XmlElement(name = "Person")
	    public List<Person>getPersons() {
	        return persons;
	    }

	    public void setPersons(List<Person> persons) {
	        this.persons = persons;
	    }
}
