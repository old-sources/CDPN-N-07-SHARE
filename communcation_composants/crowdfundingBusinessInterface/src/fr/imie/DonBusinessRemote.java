package fr.imie;

import java.util.List;

import javax.ejb.Remote;

import crowdfundingPersistence.Don;


public interface DonBusinessRemote {
	List<Don> getOneDon(Integer id);

	Don createOneDon(Don don);
}
