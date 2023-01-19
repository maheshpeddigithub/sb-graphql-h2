package com.testproj.graphql.resolver;

import com.testproj.graphql.model.Author;
import com.testproj.graphql.model.Tutorial;
import com.testproj.graphql.repository.AuthorRepository;
import com.testproj.graphql.repository.TutorialRepository;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {
	private AuthorRepository authorRepository;
	private TutorialRepository tutorialRepository;

	public Query(AuthorRepository authorRepository, TutorialRepository tutorialRepository) {
		this.authorRepository = authorRepository;
		this.tutorialRepository = tutorialRepository;
	}

	public Iterable<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public Iterable<Tutorial> findAllTutorials() {
		return tutorialRepository.findAll();
	}

	public long countAuthors() {
		return authorRepository.count();
	}

	public long countTutorials() {
		return tutorialRepository.count();
	}

}
