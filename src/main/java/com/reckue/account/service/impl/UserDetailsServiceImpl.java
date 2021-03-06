package com.reckue.account.service.impl;

import com.reckue.account.exception.NotFoundException;
import com.reckue.account.model.Account;
import com.reckue.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class UserDetailsServiceImplement represents a UserDetailsService realization
 * which loads user-specific data.
 *
 * @author Kamila Meshcheryakova
 */
@Service(value = "userDetailsService")
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    /**
     * This method is used to locate the account based on the username.
     *
     * @param username the username identifying the account whose data is required
     * @return a fully populated account record
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException(String.format("The account by username %s not found.", username),
                        HttpStatus.NOT_FOUND));

        if (account == null)
            throw new BadCredentialsException("Bad Credentials");
        new AccountStatusUserDetailsChecker().check(account);

        return account;
    }
}
