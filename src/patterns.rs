use std::collections::HashMap;
use ilog::IntLog;
use is_prime::is_prime;

pub struct DigitsPattern {
    pub emoji: &'static str,
}

pub fn match_digit_patterns(id: &u64) -> Vec<DigitsPattern> {
    let matches = vec![
        match_repeating_digits(id),
        match_fixed_suffixes(id),
        match_primes(id),
    ];

    return matches.into_iter()
        .filter(|pattern| pattern.is_some())
        .map(|pattern| pattern.unwrap())
        .collect();
}

fn match_repeating_digits(id: &u64) -> Option<DigitsPattern> {
    return match count_repeating_trailing_digits(id) {
        1 => None,
        2 => Some(DigitsPattern { emoji: "2\u{FE0F}\u{20E3}" }),
        3 => Some(DigitsPattern { emoji: "3\u{FE0F}\u{20E3}" }),
        4 => Some(DigitsPattern { emoji: "4\u{FE0F}\u{20E3}" }),
        5 => Some(DigitsPattern { emoji: "5\u{FE0F}\u{20E3}" }),
        6 => Some(DigitsPattern { emoji: "6\u{FE0F}\u{20E3}" }),
        7 => Some(DigitsPattern { emoji: "7\u{FE0F}\u{20E3}" }),
        8 => Some(DigitsPattern { emoji: "8\u{FE0F}\u{20E3}" }),
        9 => Some(DigitsPattern { emoji: "9\u{FE0F}\u{20E3}" }),
        _ => Some(DigitsPattern { emoji: "\u{267E}\u{FE0F}" }),
    };
}

fn count_repeating_trailing_digits(number: &u64) -> u64 {
    let last_digit = number % 10;
    let mut remainder = number / 10;
    let mut repeating_digits = 1;

    while (remainder % 10) == last_digit {
        repeating_digits += 1;
        remainder = remainder / 10;
    }

    return repeating_digits;
}

fn match_primes(id: &u64) -> Option<DigitsPattern> {
    if is_prime(format!("{}", id).as_str()) {
        Some(DigitsPattern { emoji: "\u{1F913}" })
    } else {
        None
    }
}

fn match_fixed_suffixes(id: &u64) -> Option<DigitsPattern> {
    let suffixes = HashMap::from([
        (69, "\u{264B}"),
        (420, "\u{1F525}"),
        (777, "\u{1F3B0}"),
        (911, "\u{2708}\u{FE0F}"),
        (1984, "\u{1F441}\u{FE0F}")
    ]);

    return suffixes.iter()
        .find(|(suffix, _)| ends_with(id, suffix))
        .map(|(_, emoji)| DigitsPattern { emoji });
}

fn ends_with(number: &u64, suffix: &u64) -> bool {
    let mask = suffix.log10() + 1;
    let reduced = number % 10u64.pow(mask as u32);

    return &reduced == suffix
}