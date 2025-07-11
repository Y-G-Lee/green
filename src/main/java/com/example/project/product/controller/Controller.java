
package com.example.project.product.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.product.dto.CreateProductDto;
import com.example.project.product.dto.FirstEnergyDto;
import com.example.project.product.dto.FirstGreenDto;
import com.example.project.product.dto.GreenObjectDto;
import com.example.project.product.dto.ProductImageDto;
import com.example.project.product.service.ObjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class Controller {

	private final ObjectService objectService;

	@GetMapping("/green-object-list")
	public List<FirstGreenDto> getGreenObjects() {
		return objectService.getAllObjects();
	}

	@GetMapping("/FirstEnergy")
	public List<FirstEnergyDto> getFirstEnergy() {
		return objectService.getAllEnergy();
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<GreenObjectDto> getProductById(@PathVariable("productId") String productId) {
		GreenObjectDto product = objectService.getId(productId);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product);
	}

	@PostMapping("/addobject")
	public void addObject(@RequestBody CreateProductDto createProductDto) {
		objectService.createObject(createProductDto);
	}

	@GetMapping("/findUserProduct")
	public List<CreateProductDto> userProduct(@RequestParam("registrationNum") String registrationNum) {
		return objectService.findUser(registrationNum);
	}

	@PostMapping("/uploadImage")
	public ResponseEntity<?> uploadImages(@RequestParam("productId") String productId,
			@RequestParam("files") List<MultipartFile> files) {
		String uploadDir = "C:/녹색제품 이미지/api/";
		List<ProductImageDto> imageDtos = new ArrayList<>();

		try {
			int index = 1;
			for (MultipartFile file : files) {
				String ext = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
						.map(f -> f.substring(f.lastIndexOf("."))).orElse(".jpg");
				String filename = productId + "_" + index++ + ext;

				File saveFile = new File(uploadDir + filename);
				file.transferTo(saveFile);

				ProductImageDto dto = new ProductImageDto();
				dto.setPId(productId);
				dto.setPImageName(filename);
				dto.setPImage(filename); 

				imageDtos.add(dto);
			}

			objectService.saveImages(imageDtos);
			return ResponseEntity.ok("이미지 저장 완료");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 저장 실패");
		}
	}
}
