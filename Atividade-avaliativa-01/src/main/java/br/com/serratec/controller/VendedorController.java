package br.com.serratec.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dtos.VendedorDTO;
import br.com.serratec.entities.Vendedor;
import br.com.serratec.service.VendedorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

	@Autowired
	private VendedorService vendedorService;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<VendedorDTO>> getAllVendedores(){
		return ResponseEntity.ok(vendedorService.getAllVendedores());
	}
	
	
	@GetMapping
	public ResponseEntity<Page<VendedorDTO>> getAllVendedores(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
		return ResponseEntity.ok(vendedorService.getAllVendedores(pageable));
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<Set<VendedorDTO>> getVendedorById(@PathVariable Long id){
		Set<VendedorDTO> vendedores = vendedorService.getVendedorById(id);
		return ResponseEntity.ok(vendedores);
		
	}
	@PostMapping
	public ResponseEntity<Vendedor> saveVendedor(@Valid @RequestBody Vendedor vendedor){
		return ResponseEntity.created(null).body(vendedorService.saveVendedor(vendedor));
	}
	
	@PostMapping("/saveAll")
	public ResponseEntity<List<Vendedor>> saveAll(@Valid @RequestBody List<Vendedor> vendedores){
		vendedores = vendedorService.saveAll(vendedores);
		return ResponseEntity.ok(vendedores);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Vendedor> updateVenda(@PathVariable Long id,@Valid @RequestBody Vendedor vendedor){
		vendedor = vendedorService.updateVendedor(id, vendedor);
		return ResponseEntity.ok(vendedor);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletevendedor(@PathVariable Long id){
		vendedorService.deleteVendedor(id);
		return ResponseEntity.noContent().build();
	}
	
}
