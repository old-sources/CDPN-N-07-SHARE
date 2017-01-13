package fr.imie;

import java.util.List;

import crowdfundingPersistence.Projet;

public interface ProjetBusinessRemote {
	List<Projet> getAllProjects();

	Projet getOneProject(Integer id);

	Boolean deleteOneProject(Integer id);

	Projet createOneProject(Projet projet);

	Projet updateOneProject(Projet projet, Integer id);

}
