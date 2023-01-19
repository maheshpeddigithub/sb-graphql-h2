package com.testproj.graphql.resolver;

import java.util.Optional;

import com.testproj.graphql.model.Author;
import com.testproj.graphql.model.Tutorial;
import com.testproj.graphql.repository.AuthorRepository;
import com.testproj.graphql.repository.TutorialRepository;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import javassist.NotFoundException;

@Component
public class Mutation implements GraphQLMutationResolver {
	private AuthorRepository authorRepository;
	private TutorialRepository tutorialRepository;

	public Mutation(AuthorRepository authorRepository, TutorialRepository tutorialRepository) {
		this.authorRepository = authorRepository;
		this.tutorialRepository = tutorialRepository;
	}

	public Author createAuthor(String name, Integer age) {
		Author author = Author.builder().name(name).age(age).build();
		authorRepository.save(author);
		return author;
	}

	public Tutorial createTutorial(String title, String description, Long authorId) {
		Tutorial tutorial = Tutorial.builder().author(new Author(authorId)).title(title).description(description).build();
		tutorialRepository.save(tutorial);
		return tutorial;
	}

	public boolean deleteTutorial(Long id) {
		tutorialRepository.deleteById(id);
		return true;
	}

	public Tutorial updateTutorial(Long id, String title, String description) throws NotFoundException {
		Optional<Tutorial> optTutorial = tutorialRepository.findById(id);

		if (optTutorial.isPresent()) {
			Tutorial tutorial = optTutorial.get();

			if (title != null)
				tutorial.setTitle(title);
			if (description != null)
				tutorial.setDescription(description);

			tutorialRepository.save(tutorial);
			return tutorial;
		}

		throw new NotFoundException("Not found Tutorial to update!");
	}
}
