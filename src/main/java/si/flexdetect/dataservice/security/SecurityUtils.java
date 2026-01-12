package si.flexdetect.dataservice.security;

import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {}

    public static Integer userId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("Unauthenticated");
        }

        Object principal = auth.getPrincipal();

        if (!(principal instanceof Integer)) {
            throw new IllegalStateException("Invalid principal type: " + principal);
        }

        return (Integer) principal;
    }

}
