package VotingApplication.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
public enum Permission {
    QUESTION_READ("QUESTION_READ"),
    QUESTION_UPDATE("QUESTION_UPDATE"),
    QUESTION_DELETE("QUESTION_DELETE"),
    QUESTION_ADD("QUESTION_ADD"),
    USER_READ("USER_READ"),
    USER_ADD("USER_ADD"),
    USER_EDIT("USER_EDIT"),
    USER_DELETE("USER_DELETE");

    private final String permission;

}