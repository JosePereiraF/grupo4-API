package br.com.serratec.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dtos.LancamentoVendasMostrarDTO;
import br.com.serratec.entities.LancamentoVendas;
import br.com.serratec.exceptions.ResourceNotFoundException;
import br.com.serratec.exceptions.VendedorNotFoundException;
import br.com.serratec.repository.LancamentoVendasRepository;

@Service
public class LancamentoVendasService {
	
	@Autowired
	private LancamentoVendasRepository repository;
	
	public List<LancamentoVendasMostrarDTO> getAllVendas(){
		return repository.findAll().stream()
				.map(lv -> new LancamentoVendasMostrarDTO(lv.getDataVenda(),lv.getValorVenda(),lv.getVendedor().getNome()))
				.collect(Collectors.toList());
	}
	
	public Set<LancamentoVendasMostrarDTO> getVendaById(Long id) {
		LancamentoVendas venda = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Lancamento não encontrado"));
		return Stream.of(venda)
				.map(lv -> new LancamentoVendasMostrarDTO(lv.getDataVenda(),lv.getValorVenda(),lv.getVendedor().getNome()))
				.collect(Collectors.toSet());	
	}
	
	
	public LancamentoVendas saveVenda(LancamentoVendas venda) {
		if(venda.getVendedor() == null) throw new VendedorNotFoundException("vendedor não encontrado"); 
		return repository.save(venda);
	}
	public List<LancamentoVendas> saveAll(List<LancamentoVendas> vendas){
		//pegar a lista que foi enviada e verificar se algum vendedor é nulo se for gerar um erro
		return repository.saveAll(vendas);
	}
	
	public LancamentoVendas updateVenda(Long id, LancamentoVendas venda) {
		LancamentoVendas vendas = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Lancamento não encontrado"));
		venda.setCodigoVenda(id);
		return repository.save(venda);
		
	}
	public void deleteVenda(Long id) {
		LancamentoVendas vendas = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Lancamento não encontrado"));
		repository.deleteById(id);
	}
	
}
