package com.tonacs.productos.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tonacs.productos.dao.ProductosDAO;
import com.tonacs.productos.entitys.Producto;

@RestController
@RequestMapping("producto")
public class ProductoRest {

	@Autowired
	private ProductosDAO productoDAO;

	@GetMapping
	public ResponseEntity<List<Producto>> getProducto() {

		List<Producto> productos = productoDAO.findAll();
		return ResponseEntity.ok(productos);
	}

	@RequestMapping(value = "/{productId}") // producto/productId -> /product/2
	public ResponseEntity<Producto> getProductoById(@PathVariable("productId") Long productId) {

		Optional<Producto> optionalProduct = productoDAO.findById(productId);
		if (optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		} else {
			return ResponseEntity.noContent().build();
		}

	}
	@PostMapping
	public ResponseEntity<Producto> createProduct(@RequestBody Producto product){
		Producto newProduct=productoDAO.save(product);
		return ResponseEntity.ok(newProduct);
	}
	
	@DeleteMapping(value = "/{productId}") 
	public ResponseEntity<Void> deleteProduct(@PathVariable("productId")  Long productId){
		
		productoDAO.deleteById(productId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<Producto> updateProduct(@RequestBody Producto product){
		Optional<Producto> optionalProduct = productoDAO.findById(product.getId());
		if (optionalProduct.isPresent()) {
			Producto updateProduct=optionalProduct.get();
			updateProduct.setName(product.getName());
			productoDAO.save(updateProduct);
			return ResponseEntity.ok(updateProduct);
		} else {
			return ResponseEntity.notFound() .build();
		}
	}
	

	// @GetMapping
	// @RequestMapping(value = "hello", method = RequestMethod.GET)
	public String hello() {
		return "Hola mundo";
	}
}
