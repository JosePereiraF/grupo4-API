package br.com.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.entities.LancamentoVendas;
import br.com.serratec.repository.LancamentoVendasRepository;

@Service
public class LancamentoVendasService {
	
	@Autowired
	private LancamentoVendasRepository repository;
	
	public List<LancamentoVendas> getAllVendas(){
		return repository.findAll();
	}
	
	public LancamentoVendas getVendaById(Long id) {
		Optional<LancamentoVendas> venda =  repository.findById(id);
		if(venda.isPresent()) {
			return venda.get();
		}else {
			
			return null;
		}
	}
	
	public LancamentoVendas saveVenda(LancamentoVendas venda) {
		return repository.save(venda);
	}
	
	public LancamentoVendas updateVenda(Long id, LancamentoVendas venda) {
		if(repository.existsById(id)) {
			venda.setCodigoVenda(id);
			return repository.save(venda);
		}else {
			return null;
		}
	}
	public Long deleteVenda(Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return id; 
			
		}else {
			return null;
		}
	}
	
}
