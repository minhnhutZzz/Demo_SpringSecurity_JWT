package vn.iotstar.model;


import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {

    private Long categoryId;
    private String categorycode;
    
    @NotEmpty
    @Length(min=5)
    private	 String categoryname;
    
    private String images;
    private MultipartFile imageFile;
    private boolean status;
    private Boolean isEdit = false;
}
