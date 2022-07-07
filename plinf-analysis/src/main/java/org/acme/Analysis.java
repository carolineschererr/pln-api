package org.acme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "analysis")
public class Analysis extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text", length = 1024, nullable = false)
    private String text;

    @Column(name = "searchword", length = 50, nullable = false)
    private String searchWord;

    public Analysis(){
    }
    
    public Analysis(Long id, String text, String searchWord) {
        this.id = id;
        this.text = text;
        this.searchWord = searchWord;
    }
}
