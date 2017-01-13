package fr.imie;

import java.util.List;

import javax.ejb.Local;

import crowdfundingPersistence.Don;

@Local
public interface DonBusinessLocal {

	List<Don> getOneDon(Integer id);

	Don createOneDon(Don don);


}
