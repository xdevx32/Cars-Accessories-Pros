package acho.nbu.cars.accessories.pros.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {
	
	@Autowired
	private MaterialRepository materialRepository;
	
	public List<Material> getAllMaterials() {
		List<Material> materials = new ArrayList<>();
		materialRepository.findAll()
		.forEach(materials::add);
		
		return materials;
	}
	
	public Material getMaterial(Long id) {
		Material material = materialRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Невалиден ID номер на материал: " + id));
		return material;
	}
	
	public void addMaterial(Material material) {
		materialRepository.save(material);
	}
	
	public void updateMaterial(Material material) {
		materialRepository.save(material);
	}
	
	public void deleteMaterial(Long id) {
		Material material = materialRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Невалиден ID номер на материал: " + id));
		materialRepository.delete(material);
	}

}
