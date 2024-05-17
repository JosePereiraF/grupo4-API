package br.com.serratec.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dtos.LancamentoVendasMostrarDTO;
import br.com.serratec.entities.LancamentoVendas;
import br.com.serratec.exceptions.ResourceNotFoundException;
import br.com.serratec.repository.LancamentoVendasRepository;

@Service
public class LancamentoVendasService {
	
	@Autowired
	private LancamentoVendasRepository repository;
	
	public List<LancamentoVendasMostrarDTO> getAllVendas(){
		//LocalDate dataVenda, Double valorVenda, String nomeVendedo
//		return repository.findAll();
		return repository.findAll().stream()
				.map(lv -> new LancamentoVendasMostrarDTO(lv.getDataVenda(),lv.getValorVenda(),lv.getVendedor().getNome()))
				.collect(Collectors.toList());
	}
	
	public Set<LancamentoVendasMostrarDTO> getVendaById(Long id) {
		LancamentoVendas venda =  repository.findById(id).orElse(null);
		if(venda == null) {
			return null;
		}else {
			Set<LancamentoVendasMostrarDTO> result = new HashSet<>();
			result.add(new LancamentoVendasMostrarDTO(venda.getDataVenda(),venda.getValorVenda(),venda.getVendedor().getNome()));
			return result;
		}
	}
	
//	public Set<LancamentoVendasMostrarDTO> getVendaById(Long id) {
//		LancamentoVendas venda =  repository.findById(id).orElse(null);
//		if(venda == null) {
//			return repository.findAll().stream()
//					.map(lv -> new LancamentoVendasMostrarDTO(lv.getDataVenda(),lv.getValorVenda(),lv.getVendedor().getNome()))
//					.collect(Collectors.toSet());
//
//		}else {
//
//			return null;
//		}
//	}
	
	public LancamentoVendas saveVenda(LancamentoVendas venda) {
		return repository.save(venda);
	}
	public List<LancamentoVendas> saveAll(List<LancamentoVendas> vendas){
		return repository.saveAll(vendas);
	}
	
	public LancamentoVendas updateVenda(Long id, LancamentoVendas venda) {
		if(repository.existsById(id)) {
			venda.setCodigoVenda(id);
			return repository.save(venda);
		}else {
			return null;
		}
	}
	public void deleteVenda(Long id) {
		LancamentoVendas vendas = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Lancamento não encontrado"));
		repository.deleteById(id);
	}
	
}
