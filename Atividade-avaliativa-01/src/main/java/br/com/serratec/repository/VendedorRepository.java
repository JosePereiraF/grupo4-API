package br.com.serratec.repository;

import br.com.serratec.entities.LancamentoVendas;
import br.com.serratec.entities.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{

}
