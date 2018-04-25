package br.edu.uepb.repository;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.uepb.model.PessoaModel;
import br.edu.uepb.repository.entity.PessoaEntity;
import br.edu.uepb.repository.entity.UsuarioEntity;
import br.edu.uepb.uteis.Uteis;

public class PessoaRepository {

	@Inject
	PessoaEntity pessoaEntity;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA PESSOA
	 * 
	 * @param pessoaModel
	 */
	public void SalvarNovoRegistro(PessoaModel pessoaModel) {

		entityManager = Uteis.JpaEntityManager();

		pessoaEntity = new PessoaEntity();
		pessoaEntity.setDataCadastro(LocalDateTime.now());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro());
		pessoaEntity.setSexo(pessoaModel.getSexo());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				pessoaModel.getUsuarioModel().getCodigo());

		pessoaEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(pessoaEntity);

	}
}
