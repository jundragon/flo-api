package com.example.flo.service;

import com.example.flo.domain.Member;
import com.example.flo.domain.Playlist;
import com.example.flo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }
}
