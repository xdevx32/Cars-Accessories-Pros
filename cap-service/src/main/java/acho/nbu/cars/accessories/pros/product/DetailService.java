package acho.nbu.cars.accessories.pros.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailService {

	@Autowired
	private DetailRepository detailRepository;
	
	public List<Detail> getAllDetails() {
		List<Detail> details = new ArrayList<>();
		detailRepository.findAll()
		.forEach(details::add);
		
		return details;
	}

	public Detail getDetail(Long id) {
		Detail detail = detailRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Невалиден ID номер на детайл: " + id));
		return detail;
	}

	public void addDetail(Detail detail) {
		detailRepository.save(detail);
	}

	public void updateDetail(Detail detail) {
		detailRepository.save(detail);
	}

	public void deleteDetail(Long id) {
		Detail detail = detailRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Невалиден ID номер на детайл: " + id));
		detailRepository.delete(detail);
	}

}
