package io.github.lyr2000.novel_app.pojo.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lyr.mybatisjpaplugin.annotation.CreateTime;
import com.lyr.mybatisjpaplugin.annotation.UpdateTime;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndexPage implements Serializable {
    private Long indexId;

    private String downloadUrl;

    private String downloadTitle;

    private Long bookId;

    @JsonIgnore
    private Boolean deleteStatus;

    @CreateTime
    private Date gmtCreate;

    @UpdateTime
    private Date gmtModified;

    private static final long serialVersionUID = 1L;
}