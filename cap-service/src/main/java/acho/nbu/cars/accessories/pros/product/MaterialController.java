package acho.nbu.cars.accessories.pros.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/materials")
public class MaterialController {
	@Autowired
	private MaterialService materialService;
	
	@RequestMapping("/list")
	public String getAllMaterials(@Valid Material material, BindingResult result, Model model) {

		List<Material> materials = materialService.getAllMaterials();
		model.addAttribute("materials", materials);
		
		String title = "Списък с всички материали";
        model.addAttribute("title", title);
		
		return "list-materials";
	}

	@RequestMapping("/{id}")
	public Material getMaterial(@PathVariable("id") Long id) {
		return materialService.getMaterial(id);
	}
	
    @RequestMapping(method = RequestMethod.GET, value = "add")
    public String addMaterialForm(Model model) {
        model.addAttribute("title", "Добави материал");
        model.addAttribute("material", new Material());
        return "add-material";
    }
	
	@RequestMapping(method=RequestMethod.POST, value="add")
    public String processEmployeeForm(@ModelAttribute Material material) {
        materialService.addMaterial(material);
        return "index";
    }
	
	@RequestMapping(method=RequestMethod.PUT, value="/")
	public void updateMaterial(@RequestBody Material material) {
		materialService.updateMaterial(material);
	}
	
	@RequestMapping(value="/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		materialService.deleteMaterial(id);
		return "redirect:/";
	}
}
