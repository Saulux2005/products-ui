package lt.bit.products.ui.service;

import java.util.List;

import lt.bit.products.ui.model.Supplier;
import lt.bit.products.ui.service.domain.SupplierEntity;
import lt.bit.products.ui.service.domain.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SupplierService {

  private final SupplierRepository repository;
  private final ModelMapper mapper;

  public SupplierService(SupplierRepository repository) {
    this.repository = repository;
    mapper = new ModelMapper();
  }

  public List<Supplier> getSuppliers() {
    List<SupplierEntity> supplier = repository.findAll();
    // @formatter:off
    return mapper.map(supplier, new TypeToken<List<Supplier>>() {}.getType());
    // @formatter:on
  }
}
