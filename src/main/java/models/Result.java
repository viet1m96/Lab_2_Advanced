package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "session_results")
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "jsessionid", nullable = false)
    private String sessionId;

    @Column(name = "x", nullable = false)
    private BigDecimal x;

    @Column(name = "y", nullable = false)
    private BigDecimal y;

    @Column(name = "r", nullable = false)
    private BigDecimal R;

    @Column(name = "hit", nullable = false)
    private boolean hit;

    @Column(name = "calculation_time", nullable = false)
    private Double calTime;

    @Column(name = "released_time", nullable = false)
    private LocalDateTime releaseTime;

    public Result(String jsessionid, BigDecimal x, BigDecimal y, BigDecimal R, boolean hit, Double calTime, LocalDateTime releaseTime) {
        this.sessionId = jsessionid;
        this.x = x;
        this.y = y;
        this.R = R;
        this.hit = hit;
        this.calTime = calTime;
        this.releaseTime = releaseTime;
    }
}