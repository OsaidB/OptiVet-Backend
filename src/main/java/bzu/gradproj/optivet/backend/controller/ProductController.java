package bzu.gradproj.optivet.backend.controller;

import bzu.gradproj.optivet.backend.dto.*;
import bzu.gradproj.optivet.backend.service.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.ok(createdProduct);
    }


//    @PostMapping("/uploadImage")
//    public ResponseEntity<List<String>> uploadProductImages(@RequestParam("images") List<MultipartFile> files) {
//        List<String> errorMessage = new ArrayList<>();
//        errorMessage.add("File is empty");
//        List<String> imageUrls = new ArrayList<>();
//        try {
//
//            for (int i = 0; i < files.size(); i++) {
//
//                if ((files.get(i).isEmpty())) {
//                    //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
//                }
//
//                // Generate unique file name
//                String fileName = System.currentTimeMillis() + "-" + files.get(i).getOriginalFilename();
//                Path filePath = Paths.get(UPLOAD_DIR + fileName);
//
//                // Ensure upload directory exists
//                Files.createDirectories(filePath.getParent());
//
//                // Save the file to the upload directory
//                Files.write(filePath, files.get(i).getBytes());
//
//                // Construct the URL to access the image
//                String imageUrl = "/uploads/" + fileName;
//
//                imageUrls.add(imageUrl);
//
//
//            }
//            return ResponseEntity.ok(imageUrls);
//        } catch (IOException e) {
//            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
//            return null;
//        }
//
//    }














    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadProductImages(@RequestParam("image") MultipartFile file) {



        try {



                if ((file.isEmpty())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no file selected");
                }

                // Generate unique file name
                String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);

                // Ensure upload directory exists
                Files.createDirectories(filePath.getParent());

                // Save the file to the upload directory
                Files.write(filePath, file.getBytes());

                // Construct the URL to access the image
                String imageUrl = "/uploads/" + fileName;





            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");

        }

    }




    @GetMapping("/uploads/{fileName}")
    public ResponseEntity<Resource> serveImage(@PathVariable String fileName) {
        //List<Resource> serveImageUrls = new ArrayList<>();
        try {

                // Define the path to the file in the upload directory
                Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
                Resource resource = new UrlResource(filePath.toUri());

                if (!resource.exists()) {
                    return ResponseEntity.notFound().build();
                }

                // Determine the content type of the image


                //serveImageUrls.add(contentType);


            String contentType = "image/jpeg"; // Adjust based on the image type, if necessary
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }






//    @GetMapping("/uploads/{fileName}")
//    public ResponseEntity<List<Resource>> serveImages(@PathVariable List<String> fileNames) {
//        List<Resource> serveImageUrls = new ArrayList<>();
//        try {
//            for (int y = 0; y < fileNames.size(); y++) {
//
//                // Define the path to the file in the upload directory
//                Path filePath = Paths.get(UPLOAD_DIR).resolve(fileNames.get(y)).normalize();
//                Resource resource = new UrlResource(filePath.toUri());
//
//                if (!resource.exists()) {
//                    return ResponseEntity.notFound().build();
//                }
//                serveImageUrls.add(resource);
//                // Determine the content type of the image
//
//
//                //serveImageUrls.add(contentType);
//
//            }
//            String contentType = "image/jpeg"; // Adjust based on the image type, if necessary
//            return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType(contentType))
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + serveImageUrls.toString() + "\"")
//                    .body(serveImageUrls);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }


//    @PostMapping("/uploadImage")
//    public ResponseEntity<List<String>> uploadPetImage(@RequestParam("image") MultipartFile file) {
//        try {
//            if (file.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
//            }
//
//            // Generate unique file name
//            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
//            Path filePath = Paths.get(UPLOAD_DIR + fileName);
//
//            // Ensure upload directory exists
//            Files.createDirectories(filePath.getParent());
//
//            // Save the file to the upload directory
//            Files.write(filePath, file.getBytes());
//
//            // Construct the URL to access the image
//            String imageUrl = "/uploads/" + fileName;
//
//            return ResponseEntity.ok(imageUrl);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
//        }
//    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long productId) {
        ProductDTO updatedProduct = productService.updateProduct(productDTO, productId);
        return ResponseEntity.ok(updatedProduct);
    }


    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}


