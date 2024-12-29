package v1.company;

import common.company.CompanyModel;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import play.Logger;
import play.db.jpa.JPAApi;

import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class JPACompanyRepository implements CompanyRepository {

	private final JPAApi jpaApi;
	private final Logger.ALogger logger = Logger.of("JPACompanyRepository");

	@Inject
	public JPACompanyRepository(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}

	@Override
	public CompletionStage<CompanyModel> getDetailById(Long id) {
		return supplyAsync(() -> wrap(entityManager -> {
			TypedQuery<CompanyModel> query = entityManager.createQuery("SELECT m from CompanyModel m where m.id = :id", CompanyModel.class).setParameter("id", id);
			try {
				return query.getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
		}));

	}

	private <T> T wrap(Function<EntityManager, T> function) {
		return jpaApi.withTransaction(function);
	}
}
