package br.com.serratec.service;

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
		Vendedor vendedor =  vendedorRepository.findById(id).orElse(null);
		if(vendedor == null) {
			return null;
		}else {
			Set<VendedorDTO> result = new HashSet<>();
			result.add(new VendedorDTO(vendedor.getNome(), vendedor.getEmail(), vendedor.getSalario(), vendedor.getComissao()));
			return result;
		}
	}
	
	public Vendedor saveVendedor(Vendedor vendedor) {
		return vendedorRepository.save(vendedor);
	}
	public List<Vendedor> saveAll(List<Vendedor> vendedor){
		return vendedorRepository.saveAll(vendedor);
	}
	
	public Vendedor updateVendedor(Long id, Vendedor vendedor) {
		if(vendedorRepository.existsById(id)) {
			vendedor.setId(id);
			return vendedorRepository.save(vendedor);
		}else {
			return null;
		}
	}
	public void deleteVendedor(Long id) {
		Vendedor vendedor = vendedorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Vendedor não encontrado"));
		vendedorRepository.deleteById(id);
	}
	
}
