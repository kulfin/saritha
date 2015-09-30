package fi.hut.soberit.agilefant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.hibernate.envers.Audited;

import fi.hut.soberit.agilefant.exception.ProductRankNotSupportedException;

@Entity
@Audited
@Table(name = "storyrank", uniqueConstraints = { @UniqueConstraint(columnNames = {
        "backlog_id", "story_id" }) })
@XmlAccessorType( XmlAccessType.NONE )        
public class StoryRank {

    private int id;
    private Story story;
    private Backlog backlog;
    private int rank;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    @ManyToOne(optional = false)
    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        if(backlog instanceof Product) {
            throw new ProductRankNotSupportedException();
        }
        this.backlog = backlog;
    }
    
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
