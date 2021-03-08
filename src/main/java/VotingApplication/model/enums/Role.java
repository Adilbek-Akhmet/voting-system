package VotingApplication.model.enums;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Set;
import java.util.stream.Collectors;

import static VotingApplication.model.enums.Permission.*;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN(Sets.newHashSet(QUESTION_READ, QUESTION_UPDATE, QUESTION_DELETE, QUESTION_ADD, USER_ADD,
            USER_EDIT, USER_DELETE, USER_READ)),
    USER(Sets.newHashSet(QUESTION_READ));

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
