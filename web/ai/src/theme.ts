import { useEffect, useMemo, useState } from 'react'
import { getThemePreference, setThemePreference } from './storage'
import type { ResolvedTheme, ThemePreference } from './types'

const THEME_OPTIONS: ThemePreference[] = ['system', 'day', 'night']

export function isThemePreference(value: string): value is ThemePreference {
  return THEME_OPTIONS.includes(value as ThemePreference)
}

export function useTheme(): {
  preference: ThemePreference
  resolvedTheme: ResolvedTheme
  setPreference: (preference: ThemePreference) => void
} {
  const initial = getThemePreference()
  const [preference, setPreferenceState] = useState<ThemePreference>(isThemePreference(initial) ? initial : 'system')
  const [systemNight, setSystemNight] = useState(() => matchMedia('(prefers-color-scheme: dark)').matches)

  useEffect(() => {
    const query = matchMedia('(prefers-color-scheme: dark)')
    const update = () => setSystemNight(query.matches)
    query.addEventListener('change', update)
    return () => query.removeEventListener('change', update)
  }, [])

  const resolvedTheme = useMemo<ResolvedTheme>(() => {
    if (preference === 'system') return systemNight ? 'night' : 'day'
    return preference
  }, [preference, systemNight])

  useEffect(() => {
    document.documentElement.dataset.theme = resolvedTheme
    document.querySelector('meta[name="theme-color"]')?.setAttribute('content', resolvedTheme === 'day' ? '#f4f1e8' : '#171715')
  }, [resolvedTheme])

  return {
    preference,
    resolvedTheme,
    setPreference: next => {
      setPreferenceState(next)
      setThemePreference(next)
    }
  }
}
