package br.com.serratec.service;

import br.com.serratec.dtos.LancamentoVendasMostrarDTO;
import br.com.serratec.dtos.VendedorDTO;
import br.com.serratec.entities.Vendedor;
import br.com.serratec.exceptions.ResourceNotFoundException;
import br.com.serratec.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VendedorService {
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	public List<VendedorDTO> getAllVendedores(){
		return vendedorRepository.findAll().stream()
				.map(vend -> new VendedorDTO(vend.getNome(),vend.getEmail(),vend.getSalario(),vend.getComissao()))
				.collect(Collectors.toList());
	}
	
	public Page<VendedorDTO> getAllVendedores(Pageable pageable) {
		return vendedorRepository.findAll(pageable)
				.map(vendedor -> new VendedorDTO(vendedor.getNome(), vendedor.getEmail(), vendedor.getSalario(), vendedor.getComissao()));
	}
	
	public Set<VendedorDTO> getVendedorById(Long id) {
		Vendedor vendedor = vendedorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Vendedor não encontrado"));
		return Stream.of(vendedor)
				.map(lv -> new VendedorDTO(lv.getNome(),lv.getEmail(),lv.getSalario(),lv.getComissao()))
				.collect(Collectors.toSet());
		
	}
	
	public Vendedor saveVendedor(Vendedor vendedor) {
		return vendedorRepository.save(vendedor);
	}
	public List<Vendedor> saveAll(List<Vendedor> vendedor){
		return vendedorRepository.saveAll(vendedor);
	}
	
	public Vendedor updateVendedor(Long id, Vendedor vendedor) {
		Vendedor vendedor1 = vendedorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Vendedor não encontrado"));
		vendedor.setId(id);
		return vendedorRepository.save(vendedor);
		
	}
	public void deleteVendedor(Long id) {
		Vendedor vendedor = vendedorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Vendedor não encontrado"));
		vendedorRepository.deleteById(id);
	}
	
}
