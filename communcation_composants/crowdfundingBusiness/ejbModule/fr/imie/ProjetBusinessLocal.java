package fr.imie;

import java.util.List;

import javax.ejb.Local;

import crowdfundingPersistence.Projet;

@Local
public interface ProjetBusinessLocal {

	List<Projet> getAllProjects();

	Projet getOneProject(Integer id);

	Boolean deleteOneProject(Integer id);

	Projet createOneProject(Projet projet);

	Projet updateOneProject(Projet projet, Integer id);

}
