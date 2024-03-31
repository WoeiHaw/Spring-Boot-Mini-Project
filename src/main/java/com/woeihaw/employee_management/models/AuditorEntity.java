package com.woeihaw.employee_management.models;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditorEntity {
//    @CreatedDate
//    @Column(name = "CreatedOn")
//    private LocalDateTime createdOn;
//
//    @CreatedBy
//    @Column(name = "CreatedBy", length = 50)
//    private String createdBy;
//
//
//    @LastModifiedDate
//    @Column(name = "UpdatedOn")
//    private LocalDateTime updatedOn;
//
//    @LastModifiedBy
//    @Column(name = "UpdatedBy", length = 50)
//    private String updatedBy;
//
//    @Column(name = "DeletedOn")
//    private LocalDateTime deletedOn;
//
//    @Column(name = "DeletedBy", length = 50)
//    private String deletedBy;
//
//    @Column(name = "isDeleted", length = 50)
//    private Boolean isDeleted = false;
//
//    public LocalDateTime getCreatedOn() {
//        return createdOn;
//    }
//
//    public void setCreatedOn(LocalDateTime createdOn) {
//        this.createdOn = createdOn;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public LocalDateTime getUpdatedOn() {
//        return updatedOn;
//    }
//
//    public void setUpdatedOn(LocalDateTime updatedOn) {
//        this.updatedOn = updatedOn;
//    }
//
//    public String getUpdatedBy() {
//        return updatedBy;
//    }
//
//    public void setUpdatedBy(String updatedBy) {
//        this.updatedBy = updatedBy;
//    }
//
//    public LocalDateTime getDeletedOn() {
//        return deletedOn;
//    }
//
//    public void setDeletedOn(LocalDateTime deletedOn) {
//        this.deletedOn = deletedOn;
//    }
//
//    public String getDeletedBy() {
//        return deletedBy;
//    }
//
//    public void setDeletedBy(String deletedBy) {
//        this.deletedBy = deletedBy;
//    }
//
//    public Boolean getDeleted() {
//        return isDeleted;
//    }
//
//    public void setDeleted(Boolean deleted) {
//        isDeleted = deleted;
//    }
    private void writeToLogging(String loginMessage){
        File file = new File("logging");
        if(file.exists()){
            if(!file.isDirectory()){
                file.mkdir();
            }
        }else{
            file.mkdir();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logging\\"+ LocalDate.now()+".txt", true));
            BufferedWriter out = new BufferedWriter(writer);
            out.write("\n"+loginMessage);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @PrePersist
    public void beforeCreate(){

        String message = LocalDateTime.now()+": Create on " + this.getClass().getSimpleName().toLowerCase()+" table";
        writeToLogging(message);

    }
    @PreUpdate
    public void beforeAnyUpdate(){
        String message = LocalDateTime.now()+": Update on " + this.getClass().getSimpleName().toLowerCase()+" table";
        writeToLogging(message);
    }

    @PreRemove
    public void beforeAnyDelete() {
        String message = LocalDateTime.now()+": Delete in " + this.getClass().getSimpleName().toLowerCase()+" table";
        writeToLogging(message);
    }


}
