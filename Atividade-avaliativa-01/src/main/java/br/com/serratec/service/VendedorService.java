package br.com.serratec.service;

import br.com.serratec.dtos.VendedorDTO;
import br.com.serratec.dtos.VendedorDTO;
import br.com.serratec.entities.Vendedor;
import br.com.serratec.entities.Vendedor;
import br.com.serratec.exceptions.ResourceNotFoundException;
import br.com.serratec.repository.VendedorRepository;
import br.com.serratec.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VendedorService {
	
	@Autowired
	private VendedorRepository repository;
	
	public List<VendedorDTO> getAllVendedores(){
		return repository.findAll().stream()
				.map(vend -> new VendedorDTO(vend.getNome(),vend.getEmail(),vend.getSalario(),vend.getComissao()))
				.collect(Collectors.toList());
	}
	
	public Set<VendedorDTO> getVendedorById(Long id) {
		Vendedor vendedor =  repository.findById(id).orElse(null);
		if(vendedor == null) {
			return null;
		}else {
			Set<VendedorDTO> result = new HashSet<>();
			result.add(new VendedorDTO(vendedor.getNome(), vendedor.getEmail(), vendedor.getSalario(), vendedor.getComissao()));
			return result;
		}
	}
	
	public Vendedor saveVendedor(Vendedor vendedor) {
		return repository.save(vendedor);
	}
	public List<Vendedor> saveAll(List<Vendedor> vendedor){
		return repository.saveAll(vendedor);
	}
	
	public Vendedor updateVendedor(Long id, Vendedor vendedor) {
		if(repository.existsById(id)) {
			vendedor.setId(id);
			return repository.save(vendedor);
		}else {
			return null;
		}
	}
	public void deleteVendedor(Long id) {
		Vendedor vendedor = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Vendedor não encontrado"));
		repository.deleteById(id);
	}
	
}
