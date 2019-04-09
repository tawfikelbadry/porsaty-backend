package com.gradteam.porsaty.controller;

import com.gradteam.porsaty.model.Company;
import com.gradteam.porsaty.service.CompanyService;
import com.gradteam.porsaty.utilities.FileUploader;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tawfik on 3/19/2018.
 */

@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "http://localhost:4200")
public class ComapnyResource {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private FileUploader fileUploader;

    @GetMapping("")
    public List<Company> allCompanies(){
        System.out.println(this.companyService.getAllCompanies().size());
        return this.companyService.getAllCompanies();
    }

    /*
   *  return -2 if email used before
   *  return -1 if company name used before
   *  return id if saved succsefully
   *  else return 0
   * */
    @PostMapping(value = "/register")
    public ResponseEntity registerCompany(@Valid @RequestBody Company company){


        long result=this.companyService.register(company);
        System.out.println(company);

        if(result==-2){
            return new ResponseEntity(-2,HttpStatus.BAD_REQUEST);
        }
        if(result==-1){
            return new ResponseEntity(-1,HttpStatus.BAD_REQUEST);
        }

        if(result>0){

            URI location= ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("companies/{id}")
                    .buildAndExpand(result).toUri();

            return new ResponseEntity(company,HttpStatus.CREATED);

        }

        return new ResponseEntity("Some error occured",HttpStatus.BAD_REQUEST);
    }



    @GetMapping("/{id}")
    public ResponseEntity getSectorCompany(@PathVariable("id")long id) {
        Company company= null;
        try {
            company = this.companyService.getCompanyById(id);
            return ResponseEntity.ok(company);
        } catch (Exception e) {
            Map<String,Object> response=new HashMap<>();
            response.put("message","this company not available");
            response.put("status_code",404);
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);        }
    }

    @PostMapping(value = "{companyId}/document-upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadCompanyDocument(@PathVariable("companyId")long companyId,@RequestParam("document") MultipartFile document){
        System.out.println(document.getOriginalFilename());
        try {
            String com_user=this.companyService.getCompanyById(companyId).getUsername();

            fileUploader.uploadFile(document,com_user);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String,Object> response=new HashMap<>();
            response.put("message","this company not available");
            response.put("status_code",404);
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("file uploaded successfully");

    }


    // show image of the company by the company id
    @GetMapping(value = "/image/{companyId}",produces = MediaType.IMAGE_JPEG_VALUE )
    public ResponseEntity showNormalUserImage(@PathVariable("companyId") long companyId){
        HttpHeaders headers = new HttpHeaders();
        byte[] media = companyService.getCompanyImage(companyId);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;

    }

    @GetMapping("/top4by-tradingvolume")
    public List<Company> getPageCompaniesOrderdByTradingVolume(){
        return this.companyService.getTop4CompaniesSortedByTradingVolume();
    }

    @GetMapping("/top-n-by-tradingvolume/{size}")
    public List<Company> getTopTradingVolumeCompaniesWithSize(@PathVariable("size") int size){
        return this.companyService.getSliceOfCompaniesSortedByTradingVolume(size);
    }

    @GetMapping("/top-n-by-SectorIdAndtradingvolume/{sectorId}/size/{size}")
    public List<Company> getTopTradingVolumeCompaniesWithSize(@PathVariable("sectorId") int sectorId,@PathVariable("size") int size){
        return this.companyService.getSliceOfCompaniesSortedByTradingVolumeForSector(sectorId,size);
    }








}
